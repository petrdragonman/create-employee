// types/error.ts
export interface ValidationErrorResponse {
  errors: Record<string, string[]>; // Field name -> array of error messages
  empty: boolean;
}

export class ValidationError extends Error {
  constructor(public readonly response: ValidationErrorResponse) {
    // Combine all error messages into one string for the Error parent
    const combinedMessage = Object.entries(response.errors)
      .flatMap(([field, errors]) => errors.map((e) => `${field}: ${e}`))
      .join("\n");

    super(combinedMessage);
    this.name = "ValidationError";
  }

  // Helper to get errors for a specific field
  getFieldErrors(field: string): string[] {
    return this.response.errors[field] || [];
  }
}

export function isValidationError(error: unknown): error is ValidationError {
  return error instanceof Error && error.name === "ValidationError";
}
