import { IdentityDocumentType } from "./member-list"

export interface MemberPost {
    num:number,
    username:string,
    firstName: string,
    lastName: string,
    identityDocumentType: IdentityDocumentType,
    identityNumber: string,
    nationality: string,
    isAccountNonLocked: boolean,
    roles: string[]
}
