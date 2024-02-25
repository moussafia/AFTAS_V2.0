import { Component, EventEmitter, OnChanges, OnDestroy, OnInit, Output, SimpleChanges } from '@angular/core';
import { Datastate, State } from '../../state/state';
import { CompetitionList } from '../../model/competition/competition-list';
import { Observable, catchError, map, of, startWith } from 'rxjs';
import { CompetitionServiceService } from '../../services/competitionService/competition-service.service';

@Component({
  selector: 'app-competition-list',
  templateUrl: './competition-list.component.html',
  styleUrl: './competition-list.component.css'
})
export class CompetitionListComponent implements OnInit, OnDestroy{
  competitionList!: State<CompetitionList[]>;
  currentPage:number=0;
  pageTotal:number=0;
  readonly dataState = Datastate;
constructor(private competitionService: CompetitionServiceService){}
ngOnInit(): void {
  this.getAllCompetition(this.currentPage)
}
getAllCompetition(page: number){
this.competitionService.get(page).pipe(
  map(response => {
    let pageTotalHeader = response.headers.get('X-Total-Page');
    if(pageTotalHeader) this.pageTotal = parseInt(pageTotalHeader);
    return ({ dataState: this.dataState.LOADED, data: response.body || []})
  }),
  startWith({ dataState:Datastate.LOADING}),
  catchError(err =>of({ dataState: this.dataState.LOADED, error: err.error}))
  ).subscribe({
    next: data =>  this.competitionList = data
  });
}
getCurrentPage(page: number){
this.currentPage = page;
this.getAllCompetition(page);
}
ngOnDestroy(): void {
    
}
}
