// Validation Service User
interface IValidationUser {
  name: string;
  clientId: string;
  password: string;
  passwordCheck: string;
  phone: string;
  email: string;
}

// UserService
interface ISignupUser {
  name: string;
  clientId: string;
  password: string;
  phone: string;
  email: string;
}

interface ISigninUser {
  clientId: string;
  password: string;
}