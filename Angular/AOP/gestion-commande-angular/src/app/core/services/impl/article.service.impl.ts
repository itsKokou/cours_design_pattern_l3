import { Injectable } from '@angular/core';
import { ArticleService } from '../article.service';
import { Observable } from 'rxjs';
import { ArticleFormCommande } from '../../models/article.model';
import { RestResponse } from '../../models/rest.response';
import { environment } from '../../../../environments/environment.development';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ArticleServiceImpl implements ArticleService {
  
  private apiUrl = `${environment.APIURL}/articles`
 
  constructor(private http: HttpClient) { //injection de dependance
  }

  findByLibelle(libelle: string): Observable<RestResponse<ArticleFormCommande>> {
    return this.http.get<RestResponse<ArticleFormCommande>>(`${this.apiUrl}/libelle/${libelle}`)
  }
}
