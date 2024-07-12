import { Component, OnInit } from '@angular/core';
import { RestResponse } from '../../models/rest.response';
import { ClientList } from '../../models/client.list';
import { ClientServiceImpl } from '../../services/impl/client.service.impl';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { PaginationComponent } from '../../../components/pagination/pagination.component';
import { PaginationModel } from '../../models/pagination.model';

@Component({
  selector: 'app-clients',
  standalone: true,
  imports: [CommonModule, RouterLink,PaginationComponent], //Pour les modules ngClass, ngIf...
  templateUrl: './clients.component.html',
  styleUrl: './clients.component.css'
})
export class ClientsComponent implements OnInit{

  dataPagination:PaginationModel={
    pages:[],
    currentPage:0
  }

  response: RestResponse<ClientList[]>|null=null // variable côté vue pour affichage

  constructor (private clientService:ClientServiceImpl){}
  
  ngOnInit(): void {
    this.refresh()
  }

  refresh(page: number=0, keyword:string=""){
    this.clientService.findAll(page, keyword).subscribe(data=>{
      this.response=data
      this.dataPagination.pages=data.pages!
      this.dataPagination.currentPage=data.currentPage!
    });// Au demarrage 
  }

  paginate(page:number){
    this.refresh(page)
  }

  searchtelephone(telephone: string) {
    if (telephone.length>=3) {
      this.refresh(0,telephone)
    }
  }
}
