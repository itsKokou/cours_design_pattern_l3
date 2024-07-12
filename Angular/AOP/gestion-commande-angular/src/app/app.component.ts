import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { ClientServiceImpl } from './core/services/impl/client.service.impl';
import { RestResponse } from './core/models/rest.response';
import { ClientList } from './core/models/client.list';
import { ClientsComponent } from './core/pages/clients/clients.component';
import { HeaderComponent } from './components/header/header.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ClientsComponent, HeaderComponent], //peut inclure d'autres composants
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent  implements OnInit{
  title = 'gestion-commande-angular';
  //response?: RestResponse<ClientList[]>
  response: RestResponse<ClientList[]>|null=null // variable côté vue pour affichage

  constructor (private clientService:ClientServiceImpl){ }
  
  ngOnInit(): void {
    this.clientService.findAll().subscribe(data=>this.response=data);// Au demarrage 
  }
}
