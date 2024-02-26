export interface MemberList {
    firstName:string,
    lastName:string,
    identityDocumentType: IdentityDocumentType,
    identityNumber: string,
    nationality: string,
    number: number,
    dateAccession: string
}

export enum IdentityDocumentType{
    CIN,CARTE_RESIDENCE,PASSPORT

}
export interface MemberResponseByNum{
    firstName: string,
    lastName: string,
    username: string,
    identityDocumentType: string,
    identityNumber: string,
    nationality: string,
    number: number,
    dateAccession: string,
    roles: string[],
    isAccountNonLocked: boolean
}