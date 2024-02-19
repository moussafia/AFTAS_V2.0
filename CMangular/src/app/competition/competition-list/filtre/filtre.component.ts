import { Component } from '@angular/core';
import { StateCompetition } from '../../../state/state';

@Component({
  selector: 'app-filtre',
  templateUrl: './filtre.component.html',
  styleUrl: './filtre.component.css'
})
export class FiltreComponent {

filtreCompetition?:string;
readonly stateCompetition= StateCompetition;

}
