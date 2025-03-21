import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../state/store";
import {
  decrement,
  increment,
  incrementByAmount,
  incrementAsync,
} from "../state/counter/counterSlice";

const Counter = () => {
  // connecting to the state
  const count = useSelector((state: RootState) => state.counter.value);
  // connectiong to dispatch
  const dispatch = useDispatch<AppDispatch>();
  return (
    <div>
      <h2>{count}</h2>
      <div>
        <button onClick={() => dispatch(incrementAsync(10))}>
          {" "}
          Increment{" "}
        </button>
        <button onClick={() => dispatch(decrement())}> Decrement </button>
      </div>
    </div>
  );
};

export default Counter;
