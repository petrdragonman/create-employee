import { createAsyncThunk, createSlice, PayloadAction } from "@reduxjs/toolkit";
import {
  deleteEmployee,
  Employee,
  getAllEmployees,
} from "../services/employeeServices";

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

// Async thunk to fetch employees
export const fetchEmployees = createAsyncThunk(
  "employees/fetchEmployees",
  async () => {
    const response = await getAllEmployees();
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
      .addCase(fetchEmployees.pending, (state) => {
        state.status = "loading";
      })
      .addCase(fetchEmployees.fulfilled, (state, action) => {
        state.status = "succeeded";
        state.employees = action.payload;
      })
      .addCase(fetchEmployees.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.error.message || "Failed to fetch employees";
      })
      .addCase(removeEmployee.fulfilled, (state, action) => {
        state.employees = state.employees.filter(
          (employee) => employee.id !== action.payload
        );
      });
  },
});

//export const { fetchEmployees, removeEmployee } = employeeSlice.actions;
export default employeeSlice.reducer;
