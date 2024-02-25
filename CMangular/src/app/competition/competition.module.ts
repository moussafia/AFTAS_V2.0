import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FiltreComponent } from './competition-list/filtre/filtre.component';
import { CardComponent } from './competition-list/card/card.component';
import { CompetitionPageComponent } from './competition-page/competition-page.component';
import { TableRankingComponent } from './competition-page/table-ranking/table-ranking.component';
import { PaginationComponent } from './competition-list/pagination/pagination.component';
import { CreateCompetitionComponent } from './create-competition/create-competition.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SuccesComponent } from './create-competition/alert/succes/succes.component';
import { ErrorComponent } from './create-competition/alert/error/error.component';
import { RouterModule } from '@angular/router';
import { CompetitionListComponent } from './competition-list/competition-list.component';



@NgModule({
  declarations: [
  CompetitionListComponent,
  FiltreComponent,
  CardComponent,
  CompetitionPageComponent,
  CompetitionPageComponent,
  TableRankingComponent,
  PaginationComponent,
  CreateCompetitionComponent,
  SuccesComponent,
  ErrorComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule
  
  ],
  exports: [
    CompetitionListComponent,
    FiltreComponent,
    CardComponent,
    CompetitionPageComponent,
    CompetitionPageComponent,
    TableRankingComponent,
    PaginationComponent,
    CreateCompetitionComponent,

  ]
})
export class CompetitionModule { }
