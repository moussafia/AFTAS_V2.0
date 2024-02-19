import { Component, EventEmitter, Input, Output } from '@angular/core';
import { State } from '../../../../state/state';
import { CompetitionList } from '../../../../model/competition/competition-list';

@Component({
  selector: 'app-succes',
  templateUrl: './succes.component.html',
  styleUrl: './succes.component.css'
})
export class SuccesComponent {
  @Input() competitionResult?:State<CompetitionList>
  @Output() sweatEventEmitter: EventEmitter<boolean> = new EventEmitter<boolean>();
  destroyComponent:boolean=false
  onDestroyComponet(): void{
  this.destroyComponent = true;
  this.sweatEventEmitter.emit(this.destroyComponent);
  }
}
