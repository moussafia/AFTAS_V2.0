import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { chunk } from '../../../model/chunkPagination';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrl: './pagination.component.css',
})
export class PaginationComponent implements OnInit {

@Input() pageTotal:number = 1;
@Output() eventEmitter: EventEmitter<number> = new EventEmitter<number>();
@Input() currentPage:number = 0;
arrayOfPages:number[]=[];
arrayPagination:number[][]=[];
indexPage:number = 0;

ngOnInit(): void {
  this.arrayPagination=chunk(Array(this.pageTotal).fill(0).map((_, i) => ++i), 5);
  this.arrayOfPages=this.arrayPagination[this.indexPage];

}
previousPage(){
  this.eventEmitter.emit(--this.currentPage)
  if(this.currentPage <= this.arrayPagination[this.indexPage][0]){
    this.arrayOfPages = [...this.arrayPagination[--this.indexPage]];

  }
}
nextPage(){
  this.eventEmitter.emit(++this.currentPage)
  let lengthArrayPage = this.arrayPagination[this.indexPage].length;
  if(this.currentPage >= this.arrayPagination[this.indexPage][lengthArrayPage - 1]){
    this.arrayOfPages = [...this.arrayPagination[++this.indexPage]];

  }
}
onChangeCurrentPage(page: number){
this.currentPage = page;
this.eventEmitter.emit(this.currentPage);
}

}
