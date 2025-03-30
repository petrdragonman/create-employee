import { createAsyncThunk, createSlice, PayloadAction } from "@reduxjs/toolkit";
import {
  deleteEmployee,
  Employee,
  getAllEmployees,
  createEmployee,
  updateEmployeeById,
  getEmployeeById,
} from "../../services/employeeServices";
import { EmployeeFormData } from "../../components/EmployeeForm/schema";
import { showToast } from "../notification/toastSlice";

interface EmployeeState {
  employees: Employee[];
  status: "idle" | "loading" | "succeeded" | "failed";
  error: string | null;
}

const initialState: EmployeeState = {
  employees: [],
  status: "idle",
  error: null,
};

const handleError = (error: unknown): string => {
  if (error instanceof Error) return error.message;
  if (typeof error === "string") return error;
  return "An unknown error occurred";
};

// Helper function for showing toast notifications
const showSuccessToast = (message: string, dispatch: any) => {
  dispatch(showToast({ message, type: "success" }));
};

const showErrorToast = (
  error: unknown,
  dispatch: any,
  defaultMessage: string
) => {
  const message = handleError(error);
  dispatch(
    showToast({
      message: message || defaultMessage,
      type: "error",
    })
  );
};

// Async Thunks
export const fetchEmployees = createAsyncThunk(
  "employees/fetchEmployees",
  async (_, { dispatch, rejectWithValue }) => {
    try {
      const employees = await getAllEmployees();
      return employees;
    } catch (error) {
      showErrorToast(error, dispatch, "Failed to fetch employees");
      return rejectWithValue(handleError(error));
    }
  }
);

export const fetchEmployeeById = createAsyncThunk(
  "employees/fetchById",
  async (id: number, { dispatch, rejectWithValue }) => {
    try {
      const employee = await getEmployeeById(id);
      showSuccessToast("Employee details loaded", dispatch);
      return employee;
    } catch (error) {
      showErrorToast(error, dispatch, "Failed to fetch employee details");
      return rejectWithValue(handleError(error));
    }
  }
);

export const createNewEmployee = createAsyncThunk(
  "employees/createNewEmployee",
  async (data: EmployeeFormData, { dispatch, rejectWithValue }) => {
    try {
      const response = await createEmployee(data);
      showSuccessToast("Employee created successfully!", dispatch);
      return response;
    } catch (error) {
      showErrorToast(error, dispatch, "Failed to create employee");
      return rejectWithValue(handleError(error));
    }
  }
);

export const updateEmployee = createAsyncThunk(
  "employees/updateEmployee",
  async (
    { id, data }: { id: number; data: EmployeeFormData },
    { dispatch, rejectWithValue }
  ) => {
    try {
      const response = await updateEmployeeById(id, data);
      showSuccessToast("Employee updated successfully!", dispatch);
      return response;
    } catch (error) {
      showErrorToast(error, dispatch, "Failed to update employee");
      return rejectWithValue(handleError(error));
    }
  }
);

export const removeEmployee = createAsyncThunk(
  "employees/removeEmployee",
  async (id: number, { dispatch, rejectWithValue }) => {
    try {
      await deleteEmployee(id);
      showSuccessToast("Employee deleted successfully", dispatch);
      return id;
    } catch (error) {
      showErrorToast(error, dispatch, "Failed to delete employee");
      return rejectWithValue(handleError(error));
    }
  }
);

const employeeSlice = createSlice({
  name: "employees",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    const handlePending = (state: EmployeeState) => {
      state.status = "loading";
      state.error = null;
    };

    const handleRejected = (state: EmployeeState, action: any) => {
      state.status = "failed";
      state.error = action.payload as string;
    };

    builder
      .addCase(fetchEmployees.pending, handlePending)
      .addCase(
        fetchEmployees.fulfilled,
        (state, action: PayloadAction<Employee[]>) => {
          state.status = "succeeded";
          state.employees = action.payload;
        }
      )
      .addCase(fetchEmployees.rejected, handleRejected)

      .addCase(
        fetchEmployeeById.fulfilled,
        (state, action: PayloadAction<Employee>) => {
          const index = state.employees.findIndex(
            (e) => e.id === action.payload.id
          );
          if (index === -1) {
            state.employees.push(action.payload);
          } else {
            state.employees[index] = action.payload;
          }
        }
      )

      .addCase(createNewEmployee.pending, handlePending)
      .addCase(
        createNewEmployee.fulfilled,
        (state, action: PayloadAction<Employee>) => {
          state.status = "succeeded";
          state.employees.push(action.payload);
        }
      )
      .addCase(createNewEmployee.rejected, handleRejected)

      .addCase(updateEmployee.pending, handlePending)
      .addCase(
        updateEmployee.fulfilled,
        (state, action: PayloadAction<Employee>) => {
          state.status = "succeeded";
          const index = state.employees.findIndex(
            (e) => e.id === action.payload.id
          );
          if (index !== -1) state.employees[index] = action.payload;
        }
      )
      .addCase(updateEmployee.rejected, handleRejected)

      .addCase(removeEmployee.pending, handlePending)
      .addCase(
        removeEmployee.fulfilled,
        (state, action: PayloadAction<number>) => {
          state.status = "succeeded";
          state.employees = state.employees.filter(
            (employee) => employee.id !== action.payload
          );
        }
      )
      .addCase(removeEmployee.rejected, handleRejected);
  },
});

export default employeeSlice.reducer;
