import { Observable } from "rxjs";
import { RestResponse } from "../models/rest.response";
import CommandeList, { CommandeCreate } from "../models/commande.list";

export interface CommandeService {
    findAll(page:number, date:string, idClient:string|null):Observable<RestResponse<CommandeList[]>>
    create(commandeCreate:CommandeCreate):Observable<RestResponse<CommandeCreate>>
}
