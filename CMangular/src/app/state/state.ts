export enum Datastate{
    LOADING, LOADED, ERROR
}
export enum StateCompetition{
    PENDING, CLOSED
}
export interface  State<T>{
    dataState: Datastate,
    data?: T,
    error?: string
}