import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CompetitionDetails } from '../../model/competition/competition-details';
import { CompetitionServiceService } from '../../services/competitionService/competition-service.service';

@Component({
  selector: 'app-competition-page',
  templateUrl: './competition-page.component.html',
  styleUrl: './competition-page.component.css'
})
export class CompetitionPageComponent implements OnInit {
  competionDetails?: CompetitionDetails;
  constructor(private competitionService: CompetitionServiceService, private route: ActivatedRoute){}
  ngOnInit(): void {
    this.getCompetionDetails()
  }
  getCompetionDetails(){
    this.route.params.subscribe(params=>{
      this.competitionService.getCompetitionByCode(params['code']).subscribe({
        next: data => this.competionDetails = data
      })
    })
  }

}
