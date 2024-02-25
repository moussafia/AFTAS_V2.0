import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LayoutComponent } from './layout.component';
import { MemberModule } from '../member/member.module';
import { ReactiveFormsModule } from '@angular/forms';
import { RouetrLayoutModule } from './rouetr-layout.module';
import { PageErrorComponent } from '../page-error/page-error.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import {RouterModule} from "@angular/router";
import { DashboardComponent } from './dashboard/dashboard.component';
import { CompetitionModule } from '../competition/competition.module';



@NgModule({
  declarations: [
    PageErrorComponent,
    DashboardComponent,
    LayoutComponent,
    NavbarComponent,
    SidebarComponent
  ],
  imports: [
    CommonModule,
    MemberModule,
    CompetitionModule,
    CommonModule,
    ReactiveFormsModule,
    RouetrLayoutModule,
    RouterModule
  ]
})
export class LayoutModuleModule { }
