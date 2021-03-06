#!/usr/bin/env python3

import os
import connexion
from flask_cors import CORS

from swagger_server import encoder


def main():
    app = connexion.App(__name__, specification_dir='./swagger/')
    app.app.json_encoder = encoder.JSONEncoder
    app.add_api('swagger.yaml', arguments={'title': 'Math Web Services'})

    CORS(app.app)
    app.run(port=os.environ.get("PORT", 8091))


if __name__ == '__main__':
    main()
