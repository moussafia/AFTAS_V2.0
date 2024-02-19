import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MemberListComponent } from './member-list/member-list.component';
import { CreateMemberComponent } from './create-member/create-member.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TableComponent } from './member-list/table/table.component';
import { SearchComponent } from './member-list/search/search.component';
import { RowTableComponent } from './member-list/table/row-table/row-table.component';
import { PaginationComponent } from './member-list/table/pagination/pagination.component';
import { SuccesComponent } from './create-member/alert/succes/succes.component';
import { ErrorComponent } from './create-member/alert/error/error.component';



@NgModule({
  declarations: [
    MemberListComponent,
    TableComponent,
    SearchComponent,
    CreateMemberComponent,
    RowTableComponent,
    PaginationComponent,
    SuccesComponent,
    ErrorComponent,
    
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule

  ],
  exports: [
    MemberListComponent,
    TableComponent,
    SearchComponent,
    CreateMemberComponent
  ]
})
export class MemberModule { }
