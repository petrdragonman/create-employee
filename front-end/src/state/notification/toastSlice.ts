import { createSlice, PayloadAction } from "@reduxjs/toolkit";

type ToastType = "error" | "success";

interface ToastState {
  message: string | null;
  type: ToastType | null;
  isVisible: boolean;
}

const initialState: ToastState = {
  message: null,
  type: null,
  isVisible: false,
};

const toastSlice = createSlice({
  name: "toast",
  initialState,
  reducers: {
    showToast: (
      state,
      action: PayloadAction<{ message: string; type: ToastType }>
    ) => {
      state.message = action.payload.message;
      state.type = action.payload.type;
      state.isVisible = true;
    },
    hideToast: (state) => {
      state.isVisible = false;
    },
  },
});

export const { showToast, hideToast } = toastSlice.actions;
export default toastSlice.reducer;
