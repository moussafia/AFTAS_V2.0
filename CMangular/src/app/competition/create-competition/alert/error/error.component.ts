import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CompetitionList } from '../../../../model/competition/competition-list';
import { State } from '../../../../state/state';

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrl: './error.component.css'
})
export class ErrorComponent {
@Input() competitionResult?:State<CompetitionList>
@Output() sweatEventEmitter: EventEmitter<boolean> = new EventEmitter<boolean>();
destroyComponent:boolean=false
onDestroyComponet():void{
this.destroyComponent = true;
this.sweatEventEmitter.emit(this.destroyComponent)
}
}
