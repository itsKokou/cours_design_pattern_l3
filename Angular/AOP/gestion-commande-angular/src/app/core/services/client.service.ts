import { Observable } from "rxjs";
import { ClientCreate, ClientFormCommande, ClientList } from "../models/client.list";
import { RestResponse } from "../models/rest.response";

export interface ClientService {
    findAll(page:number,keyword:string):Observable<RestResponse<ClientList[]>>
    findByTelephone(phone:string):Observable<RestResponse<ClientFormCommande>>
    create(clientCreate:ClientCreate):Observable<RestResponse<ClientCreate>>

}
