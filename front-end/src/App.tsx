import { BrowserRouter, Route, Routes } from "react-router";
import Counter from "./components/Counter";
import EmployeesPage from "./pages/EmployeesPage";

function App() {
  return (
    <div>
      <BrowserRouter>
        {/* <h2>create_employee_app</h2> */}
        <Routes>
          <Route path="/" element={<EmployeesPage />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
