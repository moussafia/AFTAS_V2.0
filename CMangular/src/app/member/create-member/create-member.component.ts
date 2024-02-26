import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Datastate, State } from '../../state/state';
import { CompetitionList } from '../../model/competition/competition-list';
import { MemberServiceService } from '../../services/memberService/member-service.service';
import { IdentityDocumentType, MemberList, MemberResponseByNum } from '../../model/member/member-list';
import { RoleEnum } from '../../model/enum/role';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-create-member',
  templateUrl: './create-member.component.html',
  styleUrl: './create-member.component.css'
})
export class CreateMemberComponent {
  memberFormGroupe?:FormGroup
  memberResult?:State<MemberList>;
  readonly roleEnum = RoleEnum
 constructor(private  formBuilder: FormBuilder,
  private memberService: MemberServiceService,
  private route: ActivatedRoute){}
  readonly dataState = Datastate;
  readonly identityType = IdentityDocumentType;
  memberId?: string;
  memberData?: MemberResponseByNum;
  succesMessage?: string
 ngOnInit(): void {
  this.route.paramMap.subscribe(params => {
    const id = params.get('number');
    if(id!=null)
    this.memberId = id
    console.log(this.memberId); 
  });
  if(this.memberId){
      this.memberService.getMemberByNum(this.memberId).subscribe({
        next: data=> {
          this.memberData = data
          if(this.memberData){
            this.memberFormGroupe = this.formBuilder.group({
              num:[this.memberData.number,Validators.required],
              username: [this.memberData.username,Validators.required],
              firstName: [this.memberData.firstName,Validators.required],
              lastName: [this.memberData.lastName,Validators.required],
              identityDocumentType: [this.memberData.identityDocumentType,Validators.required],
              identityNumber: [this.memberData.identityNumber,Validators.required],
              nationality: [this.memberData.nationality,Validators.required],
              isAccountNonLocked: [this.memberData.isAccountNonLocked,Validators.required],
              roles: this.formBuilder.array(this.memberData.roles)
              })
    
          }
        }
      });
     
  }
 }

createMember(){
  if(this.memberFormGroupe?.invalid) return;
this.memberService.updateMember(this.memberFormGroupe?.value).subscribe({
  next: data => {
    if(data)
    this.succesMessage = "user is updated with succes"  
  },
  error:err => {
    this.memberResult=({dataState: Datastate.ERROR, error: err.error.message})

}})
}

}

