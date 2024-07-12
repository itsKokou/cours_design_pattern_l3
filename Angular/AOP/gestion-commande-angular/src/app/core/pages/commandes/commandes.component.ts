import { Component } from '@angular/core';
import CommandeList from '../../models/commande.list';
import { RestResponse } from '../../models/rest.response';
import { CommandeServiceImpl } from '../../services/impl/commande.service.impl';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PaginationComponent } from '../../../components/pagination/pagination.component';
import { PaginationModel } from '../../models/pagination.model';

@Component({
  selector: 'app-commandes',
  standalone: true,
  imports: [RouterLink, CommonModule,PaginationComponent],
  templateUrl: './commandes.component.html',
  styleUrl: './commandes.component.css'
})
export class CommandesComponent {
  response: RestResponse<CommandeList[]>|null=null // variable côté vue pour affichage
  idClient : string|null="all"
  dataPagination:PaginationModel={
    pages:[],
    currentPage:0
  }

  constructor (private commandeService:CommandeServiceImpl, private route:ActivatedRoute){}
  
  ngOnInit(): void {
    //recuperer route active
    this.idClient = this.route.snapshot.paramMap.get("id")
    this.refresh()
  }

  refresh(page: number=0, date:string=""){
    this.commandeService.findAll(page, date, this.idClient).subscribe(data=>{
      this.response=data
      this.dataPagination.pages=data.pages!
      this.dataPagination.currentPage=data.currentPage!
    });// Au demarrage 
  }

  paginate(page:number){
    this.refresh(page)
  }

  searchbydate(date: string) {
    if (date.length>=3) {
      this.refresh(0,date)
    }
  }
}
