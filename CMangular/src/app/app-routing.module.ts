import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CompetitionListComponent } from './competition/competition-list/competition-list.component';
import { CreateCompetitionComponent } from './competition/create-competition/create-competition.component';
import { MemberListComponent } from './member/member-list/member-list.component';
import { CreateMemberComponent } from './member/create-member/create-member.component';
import { DashboardComponent } from './dashboard/dashboard.component';

const routes: Routes = [
  {path:"competitions", component: CompetitionListComponent},
  {path:"createCompetition", component: CreateCompetitionComponent},
  {path:"createMember", component: CreateMemberComponent},
  {path:"allMember", component: MemberListComponent},
  {path:"dashboard", component: DashboardComponent},
  {path:"", redirectTo:"/dashboard", pathMatch:"full"},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
