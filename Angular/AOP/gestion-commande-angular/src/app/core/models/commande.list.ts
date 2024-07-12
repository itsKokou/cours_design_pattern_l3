import { ArticleFormCommande } from "./article.model"
import { ClientFormCommande } from "./client.list"

export default interface CommandeList {
    id: number,
    date: string,
    montant: number,
    etat: EtatCommande.Encours,
    etatSuivant: EtatCommande.Terminer,
    adresse: Adresse,
    couleur: string
}

export enum EtatCommande{
    Encours, Terminer
}

export interface Adresse{
    quartier: string,
    ville: string,
    numVilla: string
}

export interface CommandeCreate{
    // articlesPanier: ArticleFormCommande[],
    // total: number,
    // client: ClientFormCommande
    articlePanier?: unknown[] | undefined;
    total?: number | null | undefined;
    client?: Partial<{
        id: any;
        nomComplet: null;
        telephone: string | null;
        adresse: any;
    }> | undefined;
}