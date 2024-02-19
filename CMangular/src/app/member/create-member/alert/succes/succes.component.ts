import { Component, EventEmitter, Input, Output } from '@angular/core';
import { State } from '../../../../state/state';
import { MemberList } from '../../../../model/member/member-list';

@Component({
  selector: 'app-succes',
  templateUrl: './succes.component.html',
  styleUrl: './succes.component.css'
})
export class SuccesComponent {
  @Input() memberResult?:State<MemberList>
  @Output() sweatEventEmitter: EventEmitter<boolean> = new EventEmitter<boolean>();
  destroyComponent:boolean=false
  onDestroyComponet(): void{
  this.destroyComponent = true;
  this.sweatEventEmitter.emit(this.destroyComponent);
  }
}
