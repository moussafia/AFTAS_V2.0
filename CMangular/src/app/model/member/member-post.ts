import { IdentityDocumentType } from "./member-list";

export interface MemberPost {
    firstName: string,
    lastName: string,
    identityDocumentType: IdentityDocumentType,
    identityNumber: string,
    nationality: string
}
