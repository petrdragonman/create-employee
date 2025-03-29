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

const getErrorMessage = (error: unknown) => {
  if (error instanceof Error) return error.message;
  if (typeof error === "string") return error;
  return "An unknown error occurred";
};

// Async thunk to fetch employees
export const fetchEmployees = createAsyncThunk(
  "employees/fetchEmployees",
  async () => {
    const response = await getAllEmployees();
    return response;
  }
);

// Async thunk to fetch single employee
export const fetchEmployeeById = createAsyncThunk(
  "employees/fetchById",
  async (id: number, { rejectWithValue }) => {
    try {
      const response = await getEmployeeById(id);
      return response;
    } catch (error) {
      return rejectWithValue((error as Error).message);
    }
  }
);

//Async thunk to create employee
// export const createNewEmployee = createAsyncThunk(
//   "employees/createNewEmployee",
//   async (data: EmployeeFormData) => {
//     const response = await createEmployee(data);
//     return response;
//   }
// );

// Async thunk to create employee
export const createNewEmployee = createAsyncThunk(
  "employees/createNewEmployee",
  async (data: EmployeeFormData, { dispatch }) => {
    try {
      const response = await createEmployee(data);
      dispatch(
        showToast({
          message: "Employee created successfully!",
          type: "success",
        })
      );
      return response;
    } catch (error) {
      const message = getErrorMessage(error);
      dispatch(
        showToast({ message: "Failed to fetch employees", type: "error" })
      );
      //throw new Error(message);
      // const message = error;
      //   error === "EMAIL_CONFLICT"
      //     ? "This email is already in use"
      //     : "Failed to create employee";

      dispatch(showToast({ message, type: "error" }));
      throw new Error(message);
    }
  }
);

// Async thunk to update existing employee
export const updateEmployee = createAsyncThunk(
  "employees/updateEmployee",
  async ({ id, data }: { id: number; data: EmployeeFormData }) => {
    const response = await updateEmployeeById(id, data);
    return response;
  }
);

// Async thunk to delete an employee
export const removeEmployee = createAsyncThunk(
  "employees/removeEmployee",
  async (id: number) => {
    await deleteEmployee(id);
    return id;
  }
);

const employeeSlice = createSlice({
  name: "employees",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      // Fetch cases
      .addCase(fetchEmployees.pending, (state) => {
        state.status = "loading";
      })
      .addCase(
        fetchEmployees.fulfilled,
        (state, action: PayloadAction<Employee[]>) => {
          state.status = "succeeded";
          state.employees = action.payload;
        }
      )
      .addCase(fetchEmployees.rejected, (state, action) => {
        state.status = "failed";
        state.error = (action.payload as string) || "Failed to fetch employees";
        return {
          ...state,
          toast: showToast({
            message: state.error,
            type: "error",
          }),
        };
      })

      // Fetch employee by id case
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

      // // Create cases
      // .addCase(createNewEmployee.pending, (state) => {
      //   state.status = "loading";
      //   state.error = null;
      // })
      // .addCase(
      //   createNewEmployee.fulfilled,
      //   (state, action: PayloadAction<Employee>) => {
      //     state.status = "succeeded";
      //     state.employees.push(action.payload);
      //   }
      // )
      // .addCase(createNewEmployee.rejected, (state, action) => {
      //   state.status = "failed";
      //   //state.error = (action.payload as string) || "Failed to create employee";
      //   state.error =
      //     action.payload === "EMAIL_CONFLICT"
      //       ? "This email is already in use. Please use a different email address."
      //       : "Failed to create employee";

      //   return {
      //     ...state,
      //     toast: showToast({
      //       message: "Employee created successfully!",
      //       type: "success",
      //     }),
      //   };
      // })

      // Create cases
      .addCase(createNewEmployee.pending, (state) => {
        state.status = "loading";
        state.error = null;
      })
      .addCase(
        createNewEmployee.fulfilled,
        (state, action: PayloadAction<Employee>) => {
          state.status = "succeeded";
          state.employees.push(action.payload);
        }
      )
      .addCase(createNewEmployee.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.error.message || "Failed to create employee";
      })

      // Update cases
      .addCase(updateEmployee.pending, (state) => {
        state.status = "loading";
      })
      .addCase(
        updateEmployee.fulfilled,
        (state, action: PayloadAction<Employee>) => {
          state.status = "succeeded";
          const index = state.employees.findIndex(
            (e) => e.id === action.payload.id
          );
          if (index !== -1) {
            state.employees[index] = action.payload;
          }
        }
      )
      .addCase(updateEmployee.rejected, (state, action) => {
        state.status = "failed";
        state.error = (action.payload as string) || "Failed to update employee";
      })

      // Delete cases
      .addCase(removeEmployee.pending, (state) => {
        state.status = "loading";
      })
      .addCase(
        removeEmployee.fulfilled,
        (state, action: PayloadAction<number>) => {
          state.status = "succeeded";
          state.employees = state.employees.filter(
            (employee) => employee.id !== action.payload
          );
        }
      )
      .addCase(removeEmployee.rejected, (state, action) => {
        state.status = "failed";
        state.error = (action.payload as string) || "Failed to delete employee";
      });
  },
});

//export const { fetchEmployees, removeEmployee } = employeeSlice.actions;
export default employeeSlice.reducer;
