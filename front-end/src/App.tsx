import { BrowserRouter, Route, Routes } from "react-router";
import EmployeesPage from "./pages/EmployeesPage";
import NewEmployeePage from "./pages/NewEmployeePage/NewEmployeePage";

function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<EmployeesPage />} />
          <Route path="/employees/new" element={<NewEmployeePage />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
