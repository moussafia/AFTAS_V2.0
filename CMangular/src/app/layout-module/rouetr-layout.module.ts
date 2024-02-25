import { NgModule } from '@angular/core';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CompetitionListComponent } from '../competition/competition-list/competition-list.component';
import { CreateCompetitionComponent } from '../competition/create-competition/create-competition.component';
import { CreateMemberComponent } from '../member/create-member/create-member.component';
import { MemberListComponent } from '../member/member-list/member-list.component';
import { CompetitionPageComponent } from '../competition/competition-page/competition-page.component';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './layout.component';

const routes: Routes = [
{  path: '',
  component: LayoutComponent,
  children:[
    {path:"competitions", component: CompetitionListComponent},
    {path:"createCompetition", component: CreateCompetitionComponent},
    {path:"createMember", component: CreateMemberComponent},
    {path:"allMember", component: MemberListComponent},
    {path:"dashboard", component: DashboardComponent},
    {path:"competition/:code", component: CompetitionPageComponent},
    //{path:"", redirectTo:"error/404"}
  ]}

]

@NgModule({
  imports: [
    RouterModule.forChild(routes),

  ]
})
export class RouetrLayoutModule { }
