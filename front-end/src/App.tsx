import { BrowserRouter, Route, Routes } from "react-router";
import EmployeesPage from "./pages/EmployeesPage";

function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<EmployeesPage />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
