import instAxios from './api.js';

class ApiPiece {
    getAllPiece() {
        return instAxios.get(`/piece/getAll`);
    }

    getPiece(pieceId) {
        return instAxios.get(`/piece/${pieceId}`);
    }

    updatePiece(pieceId, piece) {
        return instAxios.put(`/piece/${pieceId}`, piece);
    }

    createPiece(piece) {
        return instAxios.post(`/piece`, piece);
    }

    deletePiece(pieceId) {
        return instAxios.delete(`/piece/${pieceId}`);
    }
}

export default new ApiPiece();