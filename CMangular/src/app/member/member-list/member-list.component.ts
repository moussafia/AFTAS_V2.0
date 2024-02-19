import { Component, OnInit } from '@angular/core';
import { MemberServiceService } from '../../services/memberService/member-service.service';
import { MemberList } from '../../model/member/member-list';
import { Datastate, State } from '../../state/state';
import { catchError, map, of, startWith } from 'rxjs';

@Component({
  selector: 'app-member-list',
  templateUrl: './member-list.component.html',
  styleUrl: './member-list.component.css'
})
export class MemberListComponent  implements OnInit{
memberList?:State<MemberList[]>;
currentPage:number=0;
pageTotal:number=0;
constructor(private memberService: MemberServiceService) {}
ngOnInit(): void {
  this.getAllMembers(this.currentPage)
}
readonly dataState = Datastate
getAllMembers(page: number):void{
  this.memberService.get(page).pipe(
    map(response=> {
        let pageTotalHeader = response.headers.get('X-Total-Page');
        if(pageTotalHeader) this.pageTotal=  parseInt(pageTotalHeader);
        return ({ dataState: this.dataState.LOADED, data: response.body || []})
      }),
      startWith({ dataState:Datastate.LOADING}),
      catchError(err => of({ dataState: this.dataState.LOADED, error: err.error}))
      ).subscribe({
        next: data =>  this.memberList = data
      });
}
getCurrentPage(page: number){
  this.currentPage = page;
  this.getAllMembers(page);
}
}
