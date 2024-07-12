import { Component, EventEmitter, Input, Output } from '@angular/core';
import { PaginationModel } from '../../core/models/pagination.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-pagination',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pagination.component.html',
  styleUrl: './pagination.component.css'
})
export class PaginationComponent {

  @Input({required:true}) data!:PaginationModel
  
  //Emetteur d'evenement
  @Output() onGetNumBerPage:EventEmitter<number>=new EventEmitter<number>()

  //Renvoyer la page au parent
  paginate(page: number) {
    this.onGetNumBerPage.emit(page)
  }
}
