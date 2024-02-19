import { CompetitionResponseDto, MemberResponseDto } from "../competition/competition-list";

export interface RankingResponse{
    competitionResponseDto: CompetitionResponseDto ,
    memberResponseDto: MemberResponseDto,
    rank: number,
    score: number
}