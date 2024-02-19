import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MemberList } from '../../../model/member/member-list';
import { State } from '../../../state/state';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrl: './table.component.css'
})
export class TableComponent {
@Input() memberList?:MemberList[];
@Input() pageTotal:number = 1;
@Output() currentPageEvent: EventEmitter<number> = new EventEmitter<number>();
@Input() currentPage:number=0;
getCurrentPageInTable(page: number){
  this.currentPage = page;
  this.currentPageEvent.emit(this.currentPage);
}
onChangeDataMember(data: State<MemberList[]>){
  console.log("hhh"+data);
  this.memberList=data.data
}
}
