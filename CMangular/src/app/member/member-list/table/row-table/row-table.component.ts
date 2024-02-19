import { Component, Input } from '@angular/core';
import { MemberList } from '../../../../model/member/member-list';

@Component({
  selector: 'app-row-table',
  templateUrl: './row-table.component.html',
  styleUrl: './row-table.component.css'
})
export class RowTableComponent {
@Input() member?:MemberList;
}
