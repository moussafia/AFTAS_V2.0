import { CompetitionResponseDto, MemberResponseDto } from "../competition/competition-list";

export interface HuntingResponse{
    fish: Fish,
    competitionResponseDto: CompetitionResponseDto,
    memberResponseDto: MemberResponseDto,
    numberOfHunting: number
}
export interface Fish{
    id: number;
    name:string;
    averageWeight:number;
}
