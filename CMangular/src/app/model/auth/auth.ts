export interface SignInRequest {
    username: string,
    password: string
}
export interface SignUpRequest {
}
export interface SignInResponse {
    access_token: string,
    refresh_token: string,
    id: number,
    firstName: string,
    LastName: string,
    username: string,
    roles: string[]
}

export interface SignUpResponse {
    message: string,
    result: any
}
export interface ErrorResponse {
    error: ErrorResponseAuth,
}
export interface ErrorResponseAuth {
    status: number,
    error: string,
    timestamp: string,
    message: string,
    path: string
}
export interface Me {
    firstName: string,
    lastName: string,
    number: 1,
    roles: string[],
    username: string
}
export interface RefreshTokenResponse {
    access_Token: string
}
export interface RefreshTokenRequest {
    refreshToken: string
}
export interface TokenDecoced{
    exp: number;
    iat: number;
    iss: string;
    roles: string; 
    scope: string;
    sub: string
    type_token: string
}