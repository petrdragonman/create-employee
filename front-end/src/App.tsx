import { BrowserRouter, Route, Routes } from "react-router";
import EmployeesPage from "./pages/EmployeesPage/EmployeesPage";
import NewEmployeePage from "./pages/NewEmployeePage/NewEmployeePage";
import EditEmployeePage from "./pages/EditEmployeePage/EditEmployeePage";

function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<EmployeesPage />} />
          <Route path="/employees" element={<EmployeesPage />} />
          <Route path="/employees/new" element={<NewEmployeePage />} />
          <Route path="/employees/:id" element={<EditEmployeePage />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
