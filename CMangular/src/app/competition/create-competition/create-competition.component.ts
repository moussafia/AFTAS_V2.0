import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CompetitionList } from '../../model/competition/competition-list';
import { Datastate, State } from '../../state/state';
import { map } from 'rxjs';
import { CompetitionServiceService } from '../../services/competitionService/competition-service.service';
@Component({
  selector: 'app-create-competition',
  templateUrl: './create-competition.component.html',
  styleUrl: './create-competition.component.css'
})
export class CreateCompetitionComponent implements OnInit {
  competitionFormGroupe?:FormGroup
  competitionResult?:State<CompetitionList>;
  destroyComponentvr:boolean=false;
 constructor(private  formBuilder: FormBuilder,
  private competitionService: CompetitionServiceService){}
  readonly dataState = Datastate;
 ngOnInit(): void {
     this.competitionFormGroupe = this.formBuilder.group({
      date:[this.formatDate(new Date(2023, 2, 11)),Validators.required],
      startTime:[null,Validators.required],
      endTime:[null,Validators.required],
      numberOfParticipants:[1, Validators.required],
      amount:[0,Validators.required],
      location:["",Validators.required]
     })
 }
 formatDate(date: Date): string {
  const day = String(date.getDate()).padStart(2, '0');
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const year = date.getFullYear();
  return `${day}/${month}/${year}`;
}
createCompetition() {
if(this.competitionFormGroupe?.invalid) return;
this.competitionService.post(this.competitionFormGroupe?.value).subscribe({
  next: data => {
    if(data.body)
    this.competitionResult = ({dataState: Datastate.LOADED, data: data.body})
  console.log(this.competitionResult);
  },
  error:err => {
    this.competitionResult=({dataState: Datastate.ERROR, error: err.error.message})
    console.log(this.competitionResult);

}})
}
destroyComponent($event: boolean){
  console.log($event);
this.destroyComponentvr = $event;
}
}
