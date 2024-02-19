import { Component, Input } from '@angular/core';
import { CompetitionList } from '../../../model/competition/competition-list';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrl: './card.component.css'
})
export class CardComponent {
@Input() competitionList?:CompetitionList
}
