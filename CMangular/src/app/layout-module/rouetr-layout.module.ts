import { NgModule } from '@angular/core';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CompetitionListComponent } from '../competition/competition-list/competition-list.component';
import { CreateCompetitionComponent } from '../competition/create-competition/create-competition.component';
import { CreateMemberComponent } from '../member/create-member/create-member.component';
import { MemberListComponent } from '../member/member-list/member-list.component';
import { CompetitionPageComponent } from '../competition/competition-page/competition-page.component';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './layout.component';
import { juryGuard } from '../guards/jury.guard';
import { managerGuard } from '../guards/manager.guard';

const routes: Routes = [
{  path: '',
  component: LayoutComponent,
  children:[
    {path:"competitions", component: CompetitionListComponent, canActivate:[juryGuard]},
    {path:"createCompetition", component: CreateCompetitionComponent, canActivate:[juryGuard]},
    {path:"member/:number", component: CreateMemberComponent, canActivate:[managerGuard]},
    {path:"allMember", component: MemberListComponent, canActivate:[managerGuard]},
    {path:"dashboard", component: DashboardComponent, canActivate: [juryGuard]},
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
