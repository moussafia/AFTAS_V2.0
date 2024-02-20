import { Component, OnInit } from '@angular/core';
import { CompetitionServiceService } from '../services/competitionService/competition-service.service';
import { CompetitionListWithRank } from '../model/competition/competition-list';
import { catchError, map, of, startWith } from 'rxjs';
import { Datastate, State } from '../state/state';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Fish } from '../model/fish/fish';
import { Hunting } from '../model/hunting';
import { FishService } from '../services/fish/fish.service';
import { HuntingResponse } from '../model/hunting/huntingResponse';
import { HuntingService } from '../services/hunting/hunting.service';
import { RankingService } from '../services/ranking/ranking.service';
import { RankingResponse } from '../model/ranking/rankingResponse';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit{
competitionData?:State<CompetitionListWithRank>;
competitionCode?: string;
memberNumber?:number;
firstName?: string;
lastName?: string;
fishId?:number
huntingFishControl?:FormGroup;
calculRankControl?:FormGroup;
huntingPost?:Hunting;
huntingResponse?:HuntingResponse;
fishList?:Fish[];
calculRankPodiumControl?:FormGroup;
stateHunting?:State<HuntingResponse>;
podiumDataResponse?:RankingResponse[];
constructor(private competitionService: CompetitionServiceService,
  private fishService:FishService,
  private formBuilder:FormBuilder,
  private huntingService: HuntingService,
  private rankService:RankingService){}
ngOnInit(): void {
    this.getcompetitionOfDayNow();
    this.getAllFish();
    this.huntingFishControl= this.formBuilder.group({
      fish_id:[this.fishId],
      average_weight:[],
      competition_code:[this.competitionCode],
      member_num:[this.memberNumber]
    })
    this.calculRankControl= this.formBuilder.group({
      competition_code:[this.competitionCode]
    })
    this.calculRankPodiumControl= this.formBuilder.group({
      competition_code:[this.competitionCode]
    })
    this.generatePodium();
}
readonly dataState = Datastate;
getcompetitionOfDayNow(){
  this.competitionService.getDetailsCompetitionOfDateNow().pipe(
    map(response=>{
        return ({dataState: this.dataState.LOADED, data: response.body || undefined})

    }),
    startWith({dataState: this.dataState.LOADING}),
    catchError(err=> of({dataState: this.dataState.ERROR, error: err.error.message}))
  ).subscribe({
    next: data => { this.competitionData = data; 
    }
  });
}
getAllFish(){
  this.fishService.getAll().subscribe({
    next:data =>{ this.fishList= data.body || []
    }
  })
}
selectIdFish(fishId: number){
  this.fishId=fishId;
}
saveHunting(){
  if(this.huntingFishControl?.invalid) return;
  this.huntingService.post(this.huntingFishControl?.value)
  .subscribe({
    next:data => {
      this.getcompetitionOfDayNow();
    },
    error: err=>{
        this.stateHunting =({dataState:this.dataState.ERROR, error: err.error.message})
    }
  })
}
calculRank(){
if(this.calculRankControl?.invalid) return;
this.rankService.get(this.calculRankControl?.value.competition_code).subscribe({
  next:data=>{
    this.getcompetitionOfDayNow();
  },
  error:err=>{
    console.log(err.error.message);
  }
})
}
generatePodium(){
if(this.calculRankPodiumControl?.invalid) return;
console.log("podium "+ this.calculRankPodiumControl?.value);
this.rankService.getPodium(this.calculRankPodiumControl?.value.competition_code).subscribe({
  next:data=>{
    this.podiumDataResponse = data.body || undefined;
    console.log(this.podiumDataResponse);
    this.getcompetitionOfDayNow();
  },
  error:err=>{
    console.log(err.error.message);
  }
})
}

}
