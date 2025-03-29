import { configureStore } from "@reduxjs/toolkit";
import employeeReducer from "../state/employee/employeeSlice";
import toastReducer from "../state/notification/toastSlice";

export const store = configureStore({
  reducer: {
    employees: employeeReducer,
    toast: toastReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;

export default store;
