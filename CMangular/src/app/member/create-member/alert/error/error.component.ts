import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MemberList } from '../../../../model/member/member-list';
import { State } from '../../../../state/state';

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrl: './error.component.css'
})
export class ErrorComponent {
  @Input() memberResult?:State<MemberList>
  @Output() sweatEventEmitter: EventEmitter<boolean> = new EventEmitter<boolean>();
  destroyComponent:boolean=false
  onDestroyComponet():void{
  this.destroyComponent = true;
  this.sweatEventEmitter.emit(this.destroyComponent)
}
}
