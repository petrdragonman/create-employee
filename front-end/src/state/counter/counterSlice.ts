import { createAsyncThunk, createSlice, PayloadAction } from "@reduxjs/toolkit";

interface CounterState {
  value: number;
}

const initialState: CounterState = {
  value: 0,
};

const counterSlice = createSlice({
  name: "counter",
  initialState,
  reducers: {
    // can take state and action
    increment: (state) => {
      state.value += 1;
    },
    decrement: (state) => {
      state.value -= 1;
    },
    // action: PayloadAction<{value: number}>
    incrementByAmount: (state, action: PayloadAction<number>) => {
      // state.value += action.payload.value
      state.value += action.payload;
    },
  },
  extraReducers: (builder) => {
    // beacuse we used Thunk we have access to fulfilled, pending, and error
    builder
      .addCase(incrementAsync.pending, () => {
        console.log("incrementAsync pending...");
      })
      .addCase(
        incrementAsync.fulfilled,
        (state, action: PayloadAction<number>) => {
          state.value += action.payload;
        }
      );
    // builder.addCase(incrementAsync.pending, (state) => {
    //   console.log("incrementAsync pending...");
    // });
    // builder.addCase(
    //   incrementAsync.fulfilled,
    //   (state, action: PayloadAction<number>) => {
    //     state.value += action.payload;
    //   }
    // );
  },
});

export const incrementAsync = createAsyncThunk(
  "counter/incrementAsync",
  async (amount: number) => {
    await new Promise((resolve) => setTimeout(resolve, 1000));
    return amount;
  }
);
export const { increment, decrement, incrementByAmount } = counterSlice.actions;

export default counterSlice.reducer;
