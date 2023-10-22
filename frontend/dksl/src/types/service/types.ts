// Validation Service User
export interface IValidationUser {
  name: string;
  clientId: string;
  password: string;
  passwordCheck: string;
  phone: string;
  email: string;
}

// UserService
export interface ISignupUser {
  name: string;
  clientId: string;
  password: string;
  phone: string;
  email: string;
}

export interface ISigninUser {
  clientId: string;
  password: string;
}
