import { Injectable } from '@angular/core';
import { ClientList } from '../../models/client.list';
import { RestResponse } from '../../models/rest.response';
import { environment } from '../../../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import CommandeList, { CommandeCreate } from '../../models/commande.list';
import { CommandeService } from '../commande.service';

@Injectable({
  providedIn: 'root'
})
export class CommandeServiceImpl implements CommandeService{
  private apiUrl = `${environment.APIURL}/commandes`
 
  constructor(private http: HttpClient) { //injection de dependance
  }
  create(commandeCreate: CommandeCreate): Observable<RestResponse<CommandeCreate>> {
    return this.http.post<RestResponse<CommandeCreate>>(`${this.apiUrl}`, commandeCreate)
  }
 
  findAll(page:number=0,date:string="",idClient:string|null): Observable<RestResponse<CommandeList[]>> {
    const url:string = idClient=="all" ? `${this.apiUrl}?page=${page}&date=${date}`:`${this.apiUrl}/client/${idClient}?page=${page}&date=${date}`
    return this.http.get<RestResponse<CommandeList[]>>(url)
  }
}
