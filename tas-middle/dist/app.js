"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.App = void 0;
const express_1 = __importDefault(require("express"));
const cors_1 = __importDefault(require("cors"));
const token_route_1 = __importDefault(require("./routes/token.route"));
const api_route_1 = __importDefault(require("./routes/api.route"));
class App {
    constructor() {
        this.express = express_1.default();
        this.middleware();
        this.routes();
    }
    middleware() {
        this.express.use(express_1.default.json());
        this.express.use(cors_1.default());
    }
    routes() {
        this.express.use('/token', token_route_1.default);
        this.express.use('/api', api_route_1.default);
    }
}
exports.App = App;
exports.default = new App().express;
//# sourceMappingURL=app.js.map