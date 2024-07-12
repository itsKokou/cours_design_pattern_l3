import { Injectable } from '@angular/core';
import { ClientCreate, ClientFormCommande, ClientList } from '../../models/client.list';
import { RestResponse } from '../../models/rest.response';
import { environment } from '../../../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ClientService } from '../client.service';

@Injectable({
  providedIn: 'root'
})
export class ClientServiceImpl implements ClientService{
  private apiUrl = `${environment.APIURL}/clients`
 
  constructor(private http: HttpClient) { //injection de dependance
  }
  findByTelephone(phone: string): Observable<RestResponse<ClientFormCommande>> {
    return this.http.get<RestResponse<ClientFormCommande>>(`${this.apiUrl}/telephone/${phone}`)
  }

  create(clientCreate: ClientCreate): Observable<RestResponse<ClientCreate>> {
    return this.http.post<RestResponse<ClientCreate>>(`${this.apiUrl}`, clientCreate)
  }
 
  findAll(page:number=0,keyword:string=""): Observable<RestResponse<ClientList[]>> {
    return this.http.get<RestResponse<ClientList[]>>(`${this.apiUrl}?page=${page}&phone=${keyword}`)
  }
}
