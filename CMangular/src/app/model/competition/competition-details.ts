export interface CompetitionResponseDto {
code: string;
date: string;
startDate: string;
endTime: string;
numberOfParticipants: number;
location: string;
amount: number;
}

export interface MemberResponseDto {
firstName: string;
lastName: string;
identityDocumentType: string;
identityNumber: string;
nationality: string | null;
number: number;
dateAccession: string;
}

export interface rankingList {
competitionResponseDto: CompetitionResponseDto;
memberResponseDto: MemberResponseDto;
rank: number;
score: number;
}

export interface CompetitionDetails {
code: string;
date: string;
startDate: string;
endTime: string;
numberOfParticipants: number;
location: string;
amount: number;
rankingList: rankingList[];
}

